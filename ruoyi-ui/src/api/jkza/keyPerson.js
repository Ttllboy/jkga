import request from '@/utils/request'

// 查询重点人员预警列表
export function listKeyPerson(query) {
  return request({
    url: '/jkza/keyPerson/list',
    method: 'get',
    params: query
  })
}

// 查询重点人员预警详细
export function getKeyPerson(id) {
  return request({
    url: '/jkza/keyPerson/' + id,
    method: 'get'
  })
}

// 新增重点人员预警
export function addKeyPerson(data) {
  return request({
    url: '/jkza/keyPerson',
    method: 'post',
    data: data
  })
}

// 修改重点人员预警
export function updateKeyPerson(data) {
  return request({
    url: '/jkza/keyPerson',
    method: 'put',
    data: data
  })
}

// 删除重点人员预警
export function delKeyPerson(id) {
  return request({
    url: '/jkza/keyPerson/' + id,
    method: 'delete'
  })
}
