import request from '@/utils/request'

// 查询设备类型列表
export function listDeviceType(query) {
  return request({
    url: '/devices/deviceType/list',
    method: 'get',
    params: query
  })
}

// 查询设备类型详细
export function getDeviceType(id) {
  return request({
    url: '/devices/deviceType/' + id,
    method: 'get'
  })
}

// 新增设备类型
export function addDeviceType(data) {
  return request({
    url: '/devices/deviceType',
    method: 'post',
    data: data
  })
}

// 修改设备类型
export function updateDeviceType(data) {
  return request({
    url: '/devices/deviceType',
    method: 'put',
    data: data
  })
}

// 删除设备类型
export function delDeviceType(id) {
  return request({
    url: '/devices/deviceType/' + id,
    method: 'delete'
  })
}
