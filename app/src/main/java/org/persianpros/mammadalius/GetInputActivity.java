package org.persianpros.mammadalius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GetInputActivity extends Activity
{
	EditText etInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_path);

		((TextView)findViewById(R.id.tvDescription)).setText(getIntent().getStringExtra("desc"));
		this.setTitle(getIntent().getStringExtra("title"));

		etInput = (EditText) findViewById(R.id.etPath);

		InputFilter[] FilterArray = new InputFilter[1];
		FilterArray[0] = new InputFilter.LengthFilter(15);
		etInput.setFilters(FilterArray);

		findViewById(R.id.btnPathOK).setOnClickListener(new OnClickListener() 
		{			
			public void onClick(View v) 
			{
				if (etInput.length() == 15)
				{
					Intent intent = getIntent();
					intent.putExtra("input", etInput.getText().toString());
					setResult(RESULT_OK, intent);
					finish();
				}
				else
					Toast.makeText(getApplicationContext(), "Text length should be \"15\"", Toast.LENGTH_LONG).show();
			}
		});
	}
}