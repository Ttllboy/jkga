import request from '@/utils/request'

// 查询地块数量列表
export function listStMassif(query) {
  return request({
    url: '/jkza/stMassif/list',
    method: 'get',
    params: query
  })
}

// 查询地块数量详细
export function getStMassif(id) {
  return request({
    url: '/jkza/stMassif/' + id,
    method: 'get'
  })
}

// 新增地块数量
export function addStMassif(data) {
  return request({
    url: '/jkza/stMassif',
    method: 'post',
    data: data
  })
}

// 修改地块数量
export function updateStMassif(data) {
  return request({
    url: '/jkza/stMassif',
    method: 'put',
    data: data
  })
}

// 删除地块数量
export function delStMassif(id) {
  return request({
    url: '/jkza/stMassif/' + id,
    method: 'delete'
  })
}
