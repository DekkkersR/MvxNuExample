﻿#pragma checksum "C:\Users\rik_d\Source\Repos\MvxNuExample\src\MvxNuExample.UWP\Views\MainView.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "05C58FAE4A2060DB882735BF69C6F122"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace MvxNuExample.UWP.Views
{
    partial class MainView : 
        global::MvxNuExample.UWP.Views.BaseView, 
        global::Windows.UI.Xaml.Markup.IComponentConnector,
        global::Windows.UI.Xaml.Markup.IComponentConnector2
    {
        /// <summary>
        /// Connect()
        /// </summary>
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Windows.UI.Xaml.Build.Tasks"," 14.0.0.0")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
        public void Connect(int connectionId, object target)
        {
            switch(connectionId)
            {
            case 1:
                {
                    global::MvxNuExample.UWP.Views.BaseView element1 = (global::MvxNuExample.UWP.Views.BaseView)(target);
                    #line 8 "..\..\..\Views\MainView.xaml"
                    ((global::MvxNuExample.UWP.Views.BaseView)element1).Loaded += this.MainView_OnLoaded;
                    #line default
                }
                break;
            case 2:
                {
                    this.RootSplitView = (global::Windows.UI.Xaml.Controls.SplitView)(target);
                    #line 48 "..\..\..\Views\MainView.xaml"
                    ((global::Windows.UI.Xaml.Controls.SplitView)this.RootSplitView).PaneClosed += this.RootSplitView_PaneClosed;
                    #line default
                }
                break;
            case 3:
                {
                    this.TogglePaneButton = (global::Windows.UI.Xaml.Controls.Primitives.ToggleButton)(target);
                    #line 64 "..\..\..\Views\MainView.xaml"
                    ((global::Windows.UI.Xaml.Controls.Primitives.ToggleButton)this.TogglePaneButton).Unchecked += this.TogglePaneButton_Unchecked;
                    #line 65 "..\..\..\Views\MainView.xaml"
                    ((global::Windows.UI.Xaml.Controls.Primitives.ToggleButton)this.TogglePaneButton).Checked += this.TogglePaneButton_Checked;
                    #line default
                }
                break;
            case 4:
                {
                    this.MenuContent = (global::Windows.UI.Xaml.Controls.Frame)(target);
                }
                break;
            case 5:
                {
                    this.PageContent = (global::Windows.UI.Xaml.Controls.Frame)(target);
                }
                break;
            default:
                break;
            }
            this._contentLoaded = true;
        }

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Windows.UI.Xaml.Build.Tasks"," 14.0.0.0")]
        [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
        public global::Windows.UI.Xaml.Markup.IComponentConnector GetBindingConnector(int connectionId, object target)
        {
            global::Windows.UI.Xaml.Markup.IComponentConnector returnValue = null;
            return returnValue;
        }
    }
}
