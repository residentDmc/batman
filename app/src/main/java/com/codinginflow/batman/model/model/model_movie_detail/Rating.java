package com.codinginflow.batman.model.model.model_movie_detail;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.codinginflow.batman.utils.AppConstants.MOVIE_DETAIL_RATING_TABLE;
import static com.codinginflow.batman.utils.AppConstants.MOVIE_DETAIL_TABLE;

@Entity(tableName = MOVIE_DETAIL_RATING_TABLE)
public class Rating {

    @ColumnInfo(name = "id")
    @PrimaryKey
    private int id;

    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("Value")
    @Expose
    private String value;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Rating( String source, String value) {
        this.source = source;
        this.value = value;
    }

    public Rating() {
    }

    public Rating(int id, String source, String value) {
        this.id = id;
        this.source = source;
        this.value = value;
    }
}
