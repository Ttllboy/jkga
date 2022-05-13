import request from '@/utils/request'

// 查询物资信息列表
export function listYjMaterial(query) {
  return request({
    url: '/jkza/yjMaterial/list',
    method: 'get',
    params: query
  })
}

// 查询物资信息详细
export function getYjMaterial(id) {
  return request({
    url: '/jkza/yjMaterial/' + id,
    method: 'get'
  })
}

// 新增物资信息
export function addYjMaterial(data) {
  return request({
    url: '/jkza/yjMaterial',
    method: 'post',
    data: data
  })
}

// 修改物资信息
export function updateYjMaterial(data) {
  return request({
    url: '/jkza/yjMaterial',
    method: 'put',
    data: data
  })
}

// 删除物资信息
export function delYjMaterial(id) {
  return request({
    url: '/jkza/yjMaterial/' + id,
    method: 'delete'
  })
}
