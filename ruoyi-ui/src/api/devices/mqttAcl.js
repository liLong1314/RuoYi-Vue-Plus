import request from '@/utils/request'

// 查询mqttUser的acl规则列表
export function listMqttAcl(query) {
  return request({
    url: '/devices/mqttAcl/list',
    method: 'get',
    params: query
  })
}

// 查询mqttUser的acl规则详细
export function getMqttAcl(id) {
  return request({
    url: '/devices/mqttAcl/' + id,
    method: 'get'
  })
}

// 新增mqttUser的acl规则
export function addMqttAcl(data) {
  return request({
    url: '/devices/mqttAcl',
    method: 'post',
    data: data
  })
}

// 修改mqttUser的acl规则
export function updateMqttAcl(data) {
  return request({
    url: '/devices/mqttAcl',
    method: 'put',
    data: data
  })
}

// 删除mqttUser的acl规则
export function delMqttAcl(id) {
  return request({
    url: '/devices/mqttAcl/' + id,
    method: 'delete'
  })
}
