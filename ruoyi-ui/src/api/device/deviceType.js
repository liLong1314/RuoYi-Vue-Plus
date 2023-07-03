import request from '@/utils/request'

// 查询功能名：设备类型的管理列表
export function listDeviceType(query) {
  return request({
    url: '/device/deviceType/list',
    method: 'get',
    params: query
  })
}

// 查询功能名：设备类型的管理详细
export function getDeviceType(id) {
  return request({
    url: '/device/deviceType/' + id,
    method: 'get'
  })
}

// 新增功能名：设备类型的管理
export function addDeviceType(data) {
  return request({
    url: '/device/deviceType',
    method: 'post',
    data: data
  })
}

// 修改功能名：设备类型的管理
export function updateDeviceType(data) {
  return request({
    url: '/device/deviceType',
    method: 'put',
    data: data
  })
}

// 删除功能名：设备类型的管理
export function delDeviceType(id) {
  return request({
    url: '/device/deviceType/' + id,
    method: 'delete'
  })
}
