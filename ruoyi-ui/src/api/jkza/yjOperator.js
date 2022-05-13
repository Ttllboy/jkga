import request from '@/utils/request'

// 查询值班人员列表
export function listYjOperator(query) {
  return request({
    url: '/jkza/yjOperator/list',
    method: 'get',
    params: query
  })
}

// 查询值班人员详细
export function getYjOperator(id) {
  return request({
    url: '/jkza/yjOperator/' + id,
    method: 'get'
  })
}

// 新增值班人员
export function addYjOperator(data) {
  return request({
    url: '/jkza/yjOperator',
    method: 'post',
    data: data
  })
}

// 修改值班人员
export function updateYjOperator(data) {
  return request({
    url: '/jkza/yjOperator',
    method: 'put',
    data: data
  })
}

// 删除值班人员
export function delYjOperator(id) {
  return request({
    url: '/jkza/yjOperator/' + id,
    method: 'delete'
  })
}
