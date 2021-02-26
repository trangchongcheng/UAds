package com.example.uads;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private PublisherAdView mPublisherAdView;
    private PublisherInterstitialAd mPublisherInterstitialAd;
    private RewardedAd mRewardedAd;
    private final String TAG = "MainActivity";
    private static Integer number = 0;
    private Button btn;
    private TextView tv;
    String webData =  "<!DOCTYPE html><html><head><meta charset=\"utf-8\">\n" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=100\"/>\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
            "<meta name=\"apple-mobile-web-app-capable\" content=\"yes\"/><title></title>\n" +
            "<head>\n" +
            "\t<script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script>\n" +
            "<script>\n" +
            "  window.googletag = window.googletag || {cmd: []};\n" +
            "  googletag.cmd.push(function() {\n" +
            "    googletag.defineSlot('/51119103/ureka', [728, 90], 'div-gpt-ad-1614246758451-0').addService(googletag.pubads());\n" +
            "    googletag.pubads().enableSingleRequest();\n" +
            "    googletag.enableServices();\n" +
            "  });\n" +
            "</script>\n" +
            "</head>\n" +
            "\t<body>\n" +
            "\n" +
            "<!-- /51119103/ureka -->\n" +
            "<div id='div-gpt-ad-1614246758451-0' style='width: 728px; height: 90px;'>\n" +
            "  <script>\n" +
            "    googletag.cmd.push(function() { googletag.display('div-gpt-ad-1614246758451-0'); });\n" +
            "  </script>\n" +
            "</div>\n" +
            "</body></html>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn = findViewById(R.id.button);
        tv = findViewById(R.id.tv);
        tv.setMovementMethod(LinkMovementMethodExt.getInstance());
        Spanned sp = Html.fromHtml("<html><h2>Xin chao</h2></html>");
        tv.setText(sp);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.rltLayout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        PublisherAdView banner = new Banner().createBanner(MainActivity.this);
        layout.addView(banner,params);
//        PublisherAdView adView = new PublisherAdView(this);
//        adView.setAdSizes(AdSize.BANNER);
//        adView.setAdUnitId("/6499/example/banner");
//        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
//        adView.loadAd(adRequest);
//        layout.addView(adView);

//        mPublisherAdView = findViewById(R.id.publisherAdView);
//        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
//        mPublisherAdView.loadAd(adRequest);

        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
        mPublisherInterstitialAd.setAdUnitId("/6499/example/interstitial");
        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());

        AdManagerAdRequest adRequest2 = new AdManagerAdRequest.Builder().build();

        RewardedAd.load(this, "/6499/example/rewarded",
                adRequest2, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d(TAG, "onAdFailedToLoad");
                    }
                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("number",number+"");
                if (number % 2 == 0) {
                    if (mPublisherInterstitialAd.isLoaded()) {
                        Log.d("TAG0", "Loaded");
                        mPublisherInterstitialAd.show();
                    } else {
                        Log.d("TAG1", "The interstitial wasn't loaded yet.");
                    }
                } else {
                    if (mRewardedAd != null) {
                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad was shown.");
                                mRewardedAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.d(TAG, "Ad failed to show.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Don't forget to set the ad reference to null so you
                                // don't show the ad a second time.
                                Log.d(TAG, "Ad was dismissed.");
                            }
                        });

                        Activity activityContext = MainActivity.this;
                        mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                // Handle the reward.
                                Log.d("TAG", "The user earned the reward.");
                                int rewardAmount = rewardItem.getAmount();
                                String rewardType = rewardItem.getType();
                            }
                        });
                    } else {
                        Log.d("TAG", "The rewarded ad wasn't ready yet.");
                    }
                }
                number++;
            }
        });


        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Log.d("TAG1", "onAdClosed");
                mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
            }

        });

     /*   mPublisherAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("TAG", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                Log.d("onAdClosed", "done");
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });*/

    }
}