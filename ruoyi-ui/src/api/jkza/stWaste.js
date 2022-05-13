import request from '@/utils/request'

// 查询固废点列表
export function listStWaste(query) {
  return request({
    url: '/jkza/stWaste/list',
    method: 'get',
    params: query
  })
}

// 查询固废点详细
export function getStWaste(id) {
  return request({
    url: '/jkza/stWaste/' + id,
    method: 'get'
  })
}

// 新增固废点
export function addStWaste(data) {
  return request({
    url: '/jkza/stWaste',
    method: 'post',
    data: data
  })
}

// 修改固废点
export function updateStWaste(data) {
  return request({
    url: '/jkza/stWaste',
    method: 'put',
    data: data
  })
}

// 删除固废点
export function delStWaste(id) {
  return request({
    url: '/jkza/stWaste/' + id,
    method: 'delete'
  })
}
