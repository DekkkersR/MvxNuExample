using Android.Content.Res;
using Android.OS;
using Android.Views;
using MvvmCross.Droid.Support.V4;
using Android.Support.V7.Widget;
using MvvmCross.Droid.Support.V7.AppCompat;
using MvvmCross.Binding.Droid.BindingContext;
using MvxNuExample.Droid.Activities;
using MvvmCross.Core.ViewModels;

namespace MvxNuExample.Droid.Fragments
{
    public abstract class BaseFragment : MvxFragment
    {
        protected Toolbar Toolbar { get; private set; }

        protected MvxActionBarDrawerToggle DrawerToggle { get; private set; }

        protected bool ShowHamburgerMenu { get; set; } = false;

        protected BaseFragment()
        {
            RetainInstance = true;
        }

        public override View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            var ignore = base.OnCreateView(inflater, container, savedInstanceState);

            var view = this.BindingInflate(FragmentId, null);

            Toolbar = view.FindViewById<Toolbar>(Resource.Id.toolbar);
            if (Toolbar != null)
            {
                var mainActivity = Activity as MainActivity;
                if (mainActivity == null) return view;

                mainActivity.SetSupportActionBar(Toolbar);

                if (ShowHamburgerMenu)
                {
                    mainActivity.SupportActionBar?.SetDisplayHomeAsUpEnabled(true);

                    DrawerToggle = new MvxActionBarDrawerToggle(
                        Activity,
                        mainActivity.DrawerLayout,
                        Toolbar,
                        Resource.String.drawer_open,
                        Resource.String.drawer_close
                    );

                    DrawerToggle.DrawerOpened += (sender, e) => mainActivity?.HideSoftKeyboard();
                    mainActivity.DrawerLayout.AddDrawerListener(DrawerToggle);
                }
            }
            return view;
        }

        protected abstract int FragmentId { get; }

        public override void OnConfigurationChanged(Configuration newConfig)
        {
            base.OnConfigurationChanged(newConfig);
            if (Toolbar != null)
            {
                DrawerToggle?.OnConfigurationChanged(newConfig);
            }
        }

        public override void OnActivityCreated(Bundle savedInstanceState)
        {
            base.OnActivityCreated(savedInstanceState);
            if (Toolbar != null)
            {
                DrawerToggle?.SyncState();
            }
        }
    }

    public abstract class BaseFragment<TViewModel> : BaseFragment 
        where TViewModel : class, IMvxViewModel
    {
        public new TViewModel ViewModel
        {
            get { return (TViewModel)base.ViewModel; }
            set { base.ViewModel = value; }
        }
    }

}