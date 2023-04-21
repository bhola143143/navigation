package com.example.navigationdrawer.notificationModel

data class ResponseData(
    var notification_list: ArrayList<Notification>,
    var total_count_per_page: Int?,
    var total_notification_count: Int?,
    var totalpages: Int?
)