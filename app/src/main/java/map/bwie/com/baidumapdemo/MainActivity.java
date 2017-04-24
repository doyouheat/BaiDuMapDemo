package map.bwie.com.baidumapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MapView mapView;
    private RadioButton bt0,bt1,bt2;
private ArrayList<RadioButton>  btlist=new ArrayList<>();
    private ArrayList<Integer>  maptype = new ArrayList<>();
    private BaiduMap baiduMap;
    private final String TAG = "MainActivity";
    private LocationClient mLocationClient;
    private BDLocationListener mBDLocationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        initViews();
        baiduMap = mapView.getMap();
        baiduMap.setTrafficEnabled(true);
        //设置地图缩放级别
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(19);
        baiduMap.setMapStatus(mapStatusUpdate);

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        // 取消监听函数
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(mBDLocationListener);
        }
    }
    private void initViews() {
        mapView = (MapView) findViewById(R.id.bmapView);
        bt0 = (RadioButton) findViewById(R.id.bt0);
        bt1 = (RadioButton) findViewById(R.id.bt1);
        bt2 = (RadioButton) findViewById(R.id.bt2);
        bt0.setOnClickListener(this);bt1.setOnClickListener(this);bt2.setOnClickListener(this);
        btlist.add(bt0);btlist.add(bt1);btlist.add(bt2);
        change(0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    public  void  change(int i){
        for (int j = 0; j < btlist.size(); j++) {
            if (i==j){
                btlist.get(i).setChecked(true);

            }else {
                btlist.get(j).setChecked(false);
            }
        }
    }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case  R.id.bt0:
                change(0);
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case  R.id.bt1:
                change(1);
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case  R.id.bt2:
                change(2);
                baiduMap.setTrafficEnabled(true);
                break;
        }
    }


}
