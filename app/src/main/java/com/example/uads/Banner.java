package com.example.uads;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

public class Banner{
    protected PublisherAdView createBanner(Context context){
        PublisherAdView adView = new PublisherAdView(context);
        adView.setAdSizes(AdSize.LEADERBOARD);
        adView.setAdUnitId("/51119103/ureka");
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        adView.loadAd(adRequest);
        return adView;
    }
}
