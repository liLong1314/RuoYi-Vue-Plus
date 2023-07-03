import request from '@/utils/request'

// 查询mqtt客户的连接鉴权，密码为sha256加密列表
export function listMqttUser(query) {
  return request({
    url: '/device/mqttUser/list',
    method: 'get',
    params: query
  })
}

// 查询mqtt客户的连接鉴权，密码为sha256加密详细
export function getMqttUser(id) {
  return request({
    url: '/device/mqttUser/' + id,
    method: 'get'
  })
}

// 新增mqtt客户的连接鉴权，密码为sha256加密
export function addMqttUser(data) {
  return request({
    url: '/device/mqttUser',
    method: 'post',
    data: data
  })
}

// 修改mqtt客户的连接鉴权，密码为sha256加密
export function updateMqttUser(data) {
  return request({
    url: '/device/mqttUser',
    method: 'put',
    data: data
  })
}

// 删除mqtt客户的连接鉴权，密码为sha256加密
export function delMqttUser(id) {
  return request({
    url: '/device/mqttUser/' + id,
    method: 'delete'
  })
}
