package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I2_8DropboxActivity extends BaseActivity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		getListView().setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				Intent intent;
				switch (position) 
				{
				case 0:
					intent = new Intent(getApplicationContext(), I2_8_1SetupDropBoxAccountActivity.class);
					intent.putExtra("resID", R.array.Setup_DropBox_Account);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), I2_8_2UseDropBoxActivity.class);
					intent.putExtra("resID", R.array.Use_DropBox);
					startActivity(intent);
					break;
				default:
					Toast.makeText(I2_8DropboxActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
					break;
				}				
			}
		});
	}
}
