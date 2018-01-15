package ll.leon.com.widget_animation_effect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ll.leon.com.widget_animation_effect.adapter.GridImageAdapter;

public class PictureActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    private List<LocalMedia> selectList = Collections.EMPTY_LIST;
    private GridImageAdapter adapter;

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            Picker.openDynamicGallery(PictureActivity.this, selectList)
                    .subscribe(image -> {

                        selectList = image;
                        for (LocalMedia media : selectList) {
                            Log.i("图片-----》", media.getPath());
                        }
                        adapter.setList(selectList);
                        adapter.notifyDataSetChanged();

                    }, Throwable::printStackTrace);

        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);
        GridLayoutManager manager = new GridLayoutManager(PictureActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        adapter = new GridImageAdapter(PictureActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(6);
        recycler.setAdapter(adapter);

//        预览
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(PictureActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(PictureActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(PictureActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

    }
}
