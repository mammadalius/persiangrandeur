package org.persianpros.mammadalius;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultDialog extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_dialog);

		final TextView tv = (TextView) findViewById(R.id.tvText);
		tv.setText(getIntent().getStringExtra("text"));
		Button btnOK = (Button) findViewById(R.id.btnOK);
		Button btnCopy = (Button) findViewById(R.id.btnCopyToClipboard);

		btnOK.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				finish();
			}
		});

		btnCopy.setOnClickListener(new OnClickListener() 
		{			
			public void onClick(View v) 
			{
				android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", tv.getText());
				clipboard.setPrimaryClip(clip);
				Toast.makeText(getApplicationContext(), "Result copied to clipboard.", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
