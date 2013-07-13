package controllers.admin;

import java.io.File;
import java.util.List;
import java.util.UUID;

import models.gallery.Gallery;
import models.gallery.Photo;
import utils.FileUtils;
import controllers.Application;

public class Photos extends Application {
    /**
     * 相册列表.
     */
    public static void gallery() {
        List<Gallery> galleries = Gallery.findAll();
        render("Admin/photo/galleries.html", galleries);
    }

    public static void editGallery(long id) {
        Gallery gallery = Gallery.findById(id);
        render("Admin/photo/gallery_edit.html", gallery);
    }

    public static void saveGallery(Gallery gallery) {
        gallery.save();
        editGallery(gallery.id);
    }

    public static void deleteGallery(long id) {
        Gallery gallery = Gallery.findById(id);
        gallery.delete();
        index();
    }

    /**
     * 照片列表.
     */
    public static void photo(long galleryID) {
        Gallery gallery = Gallery.findById(galleryID);
        render("Admin/photo/photos.html", gallery);
    }

    public static void edit(long galleryID, long id) {
        Gallery gallery = Gallery.findById(galleryID);
        Photo photo = Photo.findById(id);
        render("Admin/photo/photo_edit.html", photo, gallery);
    }

    public static void save(Photo photo, long galleryID) {
        Gallery gallery = Gallery.findById(galleryID);
        String ext = FileUtils.getFileExtension(photo.image.getName());

        String dir = FileUtils.getApplicationPath("data", "photos", gallery.name);
        String name = UUID.randomUUID().toString() + "." + ext;

        FileUtils.writeTo(photo.image, dir, name);

        photo.name = name;
        photo.gallery = gallery;
        photo.path = dir + name;
        photo.save();
        photo(galleryID);
    }

    public static void delete(long id) {
        Photo photo = Photo.findById(id);
        long galleryID = photo.gallery.id;
        File file = new File(photo.path);
        if (file.exists()) {
            file.delete();
        }
        photo.delete();
        photo(galleryID);
    }
}
