package models.gallery;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity(name = "T_PHOTO")
public class Photo extends Model {
    public String name;
    // 路径相对于application.conf中的attachments.path
    public String path;
    @ManyToOne
    public Gallery gallery;

    @Transient
    public File image;
}
