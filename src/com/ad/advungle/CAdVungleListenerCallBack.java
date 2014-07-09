package com.ad.advungle;

public interface CAdVungleListenerCallBack 
{

    // Called each time a video completes. isCompletedView is true if the video was not skipped.
	public void onVideoView(boolean isCompletedView, int watchedMillis, int videoDurationMillis);

	// Called before playing an ad.
    public void onAdStart();

    // Called when the user leaves the ad and control is returned to your application.
    public void onAdEnd();

    // Called when ad is downloaded and ready to be played.
    public void onCachedAdAvailable();

    // TODO Auto-generated method stub.
    void onAdUnavailable(String arg0);
}
