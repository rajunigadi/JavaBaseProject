package com.raju.javabaseproject.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity
class User() : Any(), Parcelable, ListItem {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("login")
    var login: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("repos_url")
    var reposUrl: String? = null

    constructor(input: Parcel) : this() {
        id = input.readInt()
        login = input.readString()
        avatarUrl = input.readString()
        url = input.readString()
        reposUrl = input.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(login)
        dest.writeString(avatarUrl)
        dest.writeString(url)
        dest.writeString(reposUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(input: Parcel): User {
                return User(input)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }
}
