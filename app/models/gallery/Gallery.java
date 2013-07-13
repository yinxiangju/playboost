package models.gallery;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity(name = "T_GALLERY")
public class Gallery extends Model {
    public String name;

    @Column(length = 1024)
    public String detail;

    @OneToMany(mappedBy = "gallery")
    public List<Photo> photos;

    public int getPhotoCount() {
        return photos.size();
    }
}
