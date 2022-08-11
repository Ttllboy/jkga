import request from '@/utils/request'

// 查询人脸抓怕列表
export function listMqRlzp(query) {
  return request({
    url: '/jkza/mqRlzp/list',
    method: 'get',
    params: query
  })
}

// 查询人脸抓怕详细
export function getMqRlzp(id) {
  return request({
    url: '/jkza/mqRlzp/' + id,
    method: 'get'
  })
}

// 新增人脸抓怕
export function addMqRlzp(data) {
  return request({
    url: '/jkza/mqRlzp',
    method: 'post',
    data: data
  })
}

// 修改人脸抓怕
export function updateMqRlzp(data) {
  return request({
    url: '/jkza/mqRlzp',
    method: 'put',
    data: data
  })
}

// 删除人脸抓怕
export function delMqRlzp(id) {
  return request({
    url: '/jkza/mqRlzp/' + id,
    method: 'delete'
  })
}
