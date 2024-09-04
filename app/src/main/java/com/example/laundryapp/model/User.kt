package com.example.laundryapp.model

class User {
    var FullNames: String = ""
    var phoneNumber: String = ""
    var email: String = ""
    var password: String = ""
    var userid: String = ""
    constructor(firstName: String,secondName: String,
                email: String,password: String,userid: String){
        this.FullNames=firstName
        this.phoneNumber=secondName
        this.email=email
        this.password=password
        this.userid=userid
    }
    constructor()
}