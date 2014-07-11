package com.ad.advungle;


import android.app.Activity;






import android.util.Log;

import com.vungle.publisher.VunglePub;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.AdConfig;


public class CAdVungle
{
	//unity 的 active.
    private Activity mActivity;


    //應用程式網站建立的 APP ID.
    private String mAppId = ""; 
    
    final VunglePub vunglePub = VunglePub.getInstance();
    
    private  EventListener mVungleListener = null;
    
    private CAdVungleListenerCallBack mListenerCB = null;
    
    /*
    @Override
	protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);

    }
    */
    
    public CAdVungle(Activity currentActivity)
    {
    	mActivity = currentActivity;
    }

    //設定應用ID.
    public void SetAppID(String id)
    {
    	mAppId = id;
    }
    
    private void InitalADPUBSDK()
    {
    	 // initialize the Publisher SDK
        vunglePub.init(mActivity, mAppId);
        
        
    }
    
    private void CreateListener()
    {
    
    	if (mVungleListener == null)
    	{
	    	mVungleListener = new EventListener()
	    	{
		        @Override
		        public void onVideoView(boolean isCompletedView, int watchedMillis, int videoDurationMillis) {
		            // Called each time a video completes. isCompletedView is true if the video was not skipped.
		        	if (mListenerCB != null)
		        	{
		        		mListenerCB.onVideoView(isCompletedView, watchedMillis, videoDurationMillis);
		        	}
		        }
		
		        @Override
		        public void onAdStart() {
		            // Called before playing an ad
		        	Log.v("CAdVungle","onAdStart");
		        	if (mListenerCB != null)
		        	{
		        		mListenerCB.onAdStart();
		        	}
		        }
		
		        @Override
		        public void onAdEnd() {
		            // Called when the user leaves the ad and control is returned to your application
		        	Log.v("CAdVungle","onAdEnd");
		        	if (mListenerCB != null)
		        	{
		        		mListenerCB.onAdEnd();
		        	}
		        }
		
		        @Override
		        public void onCachedAdAvailable() {
		            // Called when ad is downloaded and ready to be played
		            //Log.i("jordyn","cachedAdAvail");
		        	Log.v("CAdVungle","onCachedAdAvailable");
		        	if (mListenerCB != null)
		        	{
		        		mListenerCB.onCachedAdAvailable();
		        	}
		            
		        }
	
				@Override
				public void onAdUnavailable(String arg0) {
					// TODO Auto-generated method stub
					Log.v("CAdVungle","onAdUnavailable " + arg0);
		        	if (mListenerCB != null)
		        	{
		        		mListenerCB.onAdUnavailable(arg0);
		        	}
				}
	    	};
    	}
    	
    	vunglePub.setEventListener(mVungleListener);
    }

    public void onPause()
    {       
        vunglePub.onPause();
    }

 
    public void onResume() 
    {    
        vunglePub.onResume();
    }

    public void SetListenerCB(CAdVungleListenerCallBack cb)
    {
    	mListenerCB = cb;
    }
    
    private void CreateAll()
    {
    	InitalADPUBSDK();
    	CreateListener();
    	
    }
    
    // 初始化.
	public void Inital()
	{
		
        mActivity.runOnUiThread(new Runnable()
        {
            //@SuppressWarnings("deprecation")
			public void run()
            {
				CreateAll();
            }
        }); 
	}
	
	public void PlayAD(boolean enableBackButton, boolean enableSounds)
	{
		//final AdConfig overrideConfig = new AdConfig();
        //overrideConfig.setIncentivized(true);
        //overrideConfig.setSoundEnabled(enableSounds);
        //overrideConfig.setBackButtonImmediatelyEnabled(enableBackButton);
       // vunglePub.playAd(overrideConfig);

        vunglePub.playAd();		
	}
}
