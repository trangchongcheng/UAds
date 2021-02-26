package com.urekamedia.uconnect;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

public class UAds{
    public static PublisherAdView createBanner(Context context, String size){
        PublisherAdView adView = new PublisherAdView(context);
        adView.setAdSizes(AdSize.LEADERBOARD);
        adView.setAdUnitId("/51119103/ureka");
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        adView.loadAd(adRequest);
        return adView;
    }
}
