package mono;

import java.io.*;
import java.lang.String;
import java.util.Locale;
import java.util.HashSet;
import java.util.zip.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.util.Log;
import mono.android.Runtime;

public class MonoPackageManager {

	static Object lock = new Object ();
	static boolean initialized;

	static android.content.Context Context;

	public static void LoadApplication (Context context, ApplicationInfo runtimePackage, String[] apks)
	{
		synchronized (lock) {
			if (context instanceof android.app.Application) {
				Context = context;
			}
			if (!initialized) {
				android.content.IntentFilter timezoneChangedFilter  = new android.content.IntentFilter (
						android.content.Intent.ACTION_TIMEZONE_CHANGED
				);
				context.registerReceiver (new mono.android.app.NotifyTimeZoneChanges (), timezoneChangedFilter);
				
				System.loadLibrary("monodroid");
				Locale locale       = Locale.getDefault ();
				String language     = locale.getLanguage () + "-" + locale.getCountry ();
				String filesDir     = context.getFilesDir ().getAbsolutePath ();
				String cacheDir     = context.getCacheDir ().getAbsolutePath ();
				String dataDir      = getNativeLibraryPath (context);
				ClassLoader loader  = context.getClassLoader ();

				Runtime.init (
						language,
						apks,
						getNativeLibraryPath (runtimePackage),
						new String[]{
							filesDir,
							cacheDir,
							dataDir,
						},
						loader,
						new java.io.File (
							android.os.Environment.getExternalStorageDirectory (),
							"Android/data/" + context.getPackageName () + "/files/.__override__").getAbsolutePath (),
						MonoPackageManager_Resources.Assemblies,
						context.getPackageName ());
				
				mono.android.app.ApplicationRegistration.registerApplications ();
				
				initialized = true;
			}
		}
	}

	public static void setContext (Context context)
	{
		// Ignore; vestigial
	}

	static String getNativeLibraryPath (Context context)
	{
	    return getNativeLibraryPath (context.getApplicationInfo ());
	}

	static String getNativeLibraryPath (ApplicationInfo ainfo)
	{
		if (android.os.Build.VERSION.SDK_INT >= 9)
			return ainfo.nativeLibraryDir;
		return ainfo.dataDir + "/lib";
	}

	public static String[] getAssemblies ()
	{
		return MonoPackageManager_Resources.Assemblies;
	}

	public static String[] getDependencies ()
	{
		return MonoPackageManager_Resources.Dependencies;
	}

	public static String getApiPackageName ()
	{
		return MonoPackageManager_Resources.ApiPackageName;
	}
}

class MonoPackageManager_Resources {
	public static final String[] Assemblies = new String[]{
		/* We need to ensure that "MvxNuExample.Droid.dll" comes first in this list. */
		"MvxNuExample.Droid.dll",
		"Akavache.dll",
		"Akavache.Sqlite3.dll",
		"Fusillade.dll",
		"ModernHttpClient.dll",
		"MvvmCross.Binding.dll",
		"MvvmCross.Binding.Droid.dll",
		"MvvmCross.Core.dll",
		"MvvmCross.Droid.dll",
		"MvvmCross.Droid.Shared.dll",
		"MvvmCross.Droid.Support.Core.UI.dll",
		"MvvmCross.Droid.Support.Core.Utils.dll",
		"MvvmCross.Droid.Support.Fragment.dll",
		"MvvmCross.Droid.Support.V7.AppCompat.dll",
		"MvvmCross.Droid.Support.V7.RecyclerView.dll",
		"MvvmCross.Localization.dll",
		"MvvmCross.Platform.dll",
		"MvvmCross.Platform.Droid.dll",
		"Newtonsoft.Json.dll",
		"OkHttp.dll",
		"Polly.dll",
		"Punchclock.dll",
		"Splat.dll",
		"SQLitePCLRaw.batteries_e_sqlite3.dll",
		"SQLitePCLRaw.batteries_v2.dll",
		"SQLitePCLRaw.core.dll",
		"SQLitePCLRaw.lib.e_sqlite3.dll",
		"SQLitePCLRaw.provider.e_sqlite3.dll",
		"System.Reactive.Core.dll",
		"System.Reactive.Interfaces.dll",
		"System.Reactive.Linq.dll",
		"System.Reactive.PlatformServices.dll",
		"Xamarin.Android.Support.Animated.Vector.Drawable.dll",
		"Xamarin.Android.Support.Compat.dll",
		"Xamarin.Android.Support.Core.UI.dll",
		"Xamarin.Android.Support.Core.Utils.dll",
		"Xamarin.Android.Support.Design.dll",
		"Xamarin.Android.Support.Fragment.dll",
		"Xamarin.Android.Support.Media.Compat.dll",
		"Xamarin.Android.Support.v4.dll",
		"Xamarin.Android.Support.v7.AppCompat.dll",
		"Xamarin.Android.Support.v7.RecyclerView.dll",
		"Xamarin.Android.Support.Vector.Drawable.dll",
		"Mono.Android.Export.dll",
		"MvxNuExample.dll",
		"System.Runtime.dll",
		"System.Threading.Tasks.dll",
		"System.Collections.dll",
		"System.Linq.dll",
		"System.Diagnostics.Debug.dll",
		"System.IO.dll",
		"System.Threading.dll",
		"System.Runtime.Extensions.dll",
		"System.Linq.Expressions.dll",
		"System.Reflection.dll",
		"System.Reflection.Extensions.dll",
		"System.Resources.ResourceManager.dll",
		"System.Globalization.dll",
		"System.Net.Primitives.dll",
		"System.ObjectModel.dll",
		"System.Collections.Concurrent.dll",
		"System.Xml.XDocument.dll",
		"System.Runtime.Serialization.Primitives.dll",
		"System.Dynamic.Runtime.dll",
		"System.Text.RegularExpressions.dll",
		"System.Xml.ReaderWriter.dll",
		"System.Text.Encoding.dll",
		"System.Text.Encoding.Extensions.dll",
		"System.ComponentModel.dll",
		"System.Diagnostics.Tools.dll",
		"System.Runtime.InteropServices.WindowsRuntime.dll",
		"System.Runtime.InteropServices.dll",
		"System.Net.Requests.dll",
	};
	public static final String[] Dependencies = new String[]{
	};
	public static final String ApiPackageName = "Mono.Android.Platform.ApiLevel_24";
}