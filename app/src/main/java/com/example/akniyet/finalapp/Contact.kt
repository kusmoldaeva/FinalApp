package com.example.akniyet.finalapp

import android.os.Parcel
import android.os.Parcelable

class Contact : Parcelable {

    var name: String? = null
    var ID: String? = null
    var mobile: String = null
    var home: String = null
    var work: String = null

    constructor(name: String, ID: String, work: String, mobile: String, home: String) {
        this.name = name
        this.ID = ID
        this.work = work

        this.home = home
        this.mobile = mobile

    }

    constructor(source: Parcel) {
        this.name = source.readString()
        this.work = source.readString()
        this.ID = source.readString()
        this.home = source.readString()
        this.mobile = source.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(name)
        parcel.writeString(ID)
        parcel.writeString(home)
        parcel.writeString(work)
        parcel.writeString(mobile)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Contact> = object : Parcelable.Creator<Contact> {
            override fun createFromParcel(source: Parcel): Contact {
                return Contact(source)
            }

            override fun newArray(size: Int): Array<Contact?> {
                return arrayOfNulls(size)
            }
        }
    }
}
