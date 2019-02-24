package org.persianpros.mammadalius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class GetPathActivity extends Activity
{
	EditText etPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_path);
		
		((TextView)findViewById(R.id.tvDescription)).setText(getIntent().getStringExtra("desc"));
		this.setTitle(getIntent().getStringExtra("title"));

		etPath = (EditText) findViewById(R.id.etPath);
		findViewById(R.id.btnPathOK).setOnClickListener(new OnClickListener() 
		{			
			public void onClick(View v) 
			{
				Intent intent = getIntent();
				intent.putExtra("path", etPath.getText().toString());
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
}