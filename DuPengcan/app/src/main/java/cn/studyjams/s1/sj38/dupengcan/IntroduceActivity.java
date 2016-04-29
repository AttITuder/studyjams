package cn.studyjams.s1.sj38.dupengcan;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Created by dupengcan on 16-4-28.
 */
public class IntroduceActivity extends Activity {
    private Toolbar toolbar;
    private EditText sendText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce);
        sendText = (EditText) findViewById(R.id.send_text);
        setToolBar();
    }

    private void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.about));
        toolbar.setTitleTextColor(getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.onback);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * send email
     *
     * @param v
     */
    public void sendEmail(View v) {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:dupengcan@126.com"));
        data.putExtra(Intent.EXTRA_TEXT, sendText.getText().toString());
        startActivity(data);
    }

}
