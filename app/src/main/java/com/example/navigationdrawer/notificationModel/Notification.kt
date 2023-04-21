package com.example.navigationdrawer.notificationModel

data class Notification(
    var category_id: String?,
    var category_name: String?,
    var date: String?,
    var description: String?,
    var id: String?,
    var is_read: Boolean?,
    var notification_type: String?,
    var product_id: String?,
    var sku: String?,
    var title: String?
)