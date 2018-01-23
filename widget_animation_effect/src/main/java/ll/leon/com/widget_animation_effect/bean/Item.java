package ll.leon.com.widget_animation_effect.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Item  {

    @Id
    public long _id;
    public String title;

    @Generated(hash = 342302491)
    public Item(long _id, String title) {
        this._id = _id;
        this.title = title;
    }

    @Generated(hash = 1470900980)
    public Item() {
    }

    public long getId() {
        return this._id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public long get_id() {
        return this._id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

}
