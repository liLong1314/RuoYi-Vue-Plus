import request from '@/utils/request'

// 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
export function listMqttAcl(query) {
  return request({
    url: '/device/mqttAcl/list',
    method: 'get',
    params: query
  })
}

// 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行详细
export function getMqttAcl(id) {
  return request({
    url: '/device/mqttAcl/' + id,
    method: 'get'
  })
}

// 新增mqtt客户的acl规则，符合该规则的发布/订阅才可行
export function addMqttAcl(data) {
  return request({
    url: '/device/mqttAcl',
    method: 'post',
    data: data
  })
}

// 修改mqtt客户的acl规则，符合该规则的发布/订阅才可行
export function updateMqttAcl(data) {
  return request({
    url: '/device/mqttAcl',
    method: 'put',
    data: data
  })
}

// 删除mqtt客户的acl规则，符合该规则的发布/订阅才可行
export function delMqttAcl(id) {
  return request({
    url: '/device/mqttAcl/' + id,
    method: 'delete'
  })
}
