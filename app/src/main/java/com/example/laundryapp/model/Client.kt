package com.example.laundryapp.model

class Client {
    var imageUrl:String=""
    var fullNames:String=""
    var phoneNumber:String=""
    var gender:String=""
    var age:String=""
    var bio:String=""
    var id:String=""

    constructor(imageUrl:String,firstname: String,lastname: String,
                gender: String,age: String,bio: String,id: String){
        this.imageUrl= imageUrl
        this.fullNames=firstname
        this.phoneNumber=lastname
        this.gender=gender
        this.age=age
        this.bio=bio
        this.id=id
    }
    constructor()
}