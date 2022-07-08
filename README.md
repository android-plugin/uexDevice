# uexDevice
设备插件

## 新增接口

### 弹出系统提示向用户申请隐私权限 - uexDevice.requestPermissions(params, callback)

#### 参数

const params = {
    checkOnly: 0, //是否仅检查权限，而不申请权限。1为仅检查，不传则默认为0判断后没有权限则直接申请权限。
    permissions: ["android.permission.xxx", "android.permission.yyy"], // 权限名称
}

| 权限类型 | 权限名 |
| -- | -- |
| 精准定位 | android.permission.ACCESS_FINE_LOCATION |
| 外部存储（Android11之后逐渐废弃） | android.permission.WRITE_EXTERNAL_STORAGE |
| 读取设备唯一标识等信息（Android9之后逐渐废弃） | android.permission.READ_PHONE_STATE |
| 相机权限 | android.permission.CAMERA |

#### 回调

回调参数为json对象，具体如下：

const params = {
    result: [0, 1], //权限是否授予。0为未授权，1为已授权。result数组内结果的顺序与请求时的permissions内权限一一对应，顺序相同
}
