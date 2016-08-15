package me.frsunny.sharenews;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import me.frsunny.sharenews.databinding.ActivityMainBinding;
import me.frsunny.sharenews.retrofit.RestClient;
import me.frsunny.sharenews.retrofit.param.ShortenUrlParam;
import me.frsunny.sharenews.retrofit.response.ShortenUrlResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void shortenUrlButtonClick(View v) {
        String longUrl = mBinding.longUrlEditText.getText().toString();

        if (!TextUtils.isEmpty(longUrl)) {
            getShortenUrl(longUrl);
            mBinding.longUrlEditText.setText("");

        }
    }

    private void getShortenUrl(final String longUrl) {
        Call<ShortenUrlResponse> call = RestClient.getInstance().shortUrlService.createShortenUrl(new ShortenUrlParam.Builder().longUrl(longUrl).build());
        call.enqueue(new Callback<ShortenUrlResponse>() {
            @Override
            public void onResponse(Call<ShortenUrlResponse> call, Response<ShortenUrlResponse> response) {
                try {
                    Toast.makeText(MainActivity.this, "shortenUrl: " + response.body().getId(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ShortenUrlResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
