import request from '@/utils/request'

// 查询设备的管理列表
export function listDevice(query) {
  return request({
    url: '/device/device/list',
    method: 'get',
    params: query
  })
}

// 查询设备的管理详细
export function getDevice(id) {
  return request({
    url: '/device/device/' + id,
    method: 'get'
  })
}

// 新增设备的管理
export function addDevice(data) {
  return request({
    url: '/device/device',
    method: 'post',
    data: data
  })
}

// 修改设备的管理
export function updateDevice(data) {
  return request({
    url: '/device/device',
    method: 'put',
    data: data
  })
}

// 删除设备的管理
export function delDevice(id) {
  return request({
    url: '/device/device/' + id,
    method: 'delete'
  })
}
