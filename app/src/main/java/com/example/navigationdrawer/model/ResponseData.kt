package com.example.navigationdrawer.model

data class ResponseData(
    var cmsData: List<CmsData>,
    var contactUs: String?,
    var css: String?,
    var latestNews: String?,
    var loginURL: String?,
    var returns: String?,
    var showroomLocator: String?,
    var signUPURL: String?,
    var socialMedia: List<SocialMedia>,
    var stores: List<Store>,
    var virtualTourURL: String?
)