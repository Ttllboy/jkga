import request from '@/utils/request'

// 查询医院信息列表
export function listYyYy(query) {
  return request({
    url: '/jkza/yyYy/list',
    method: 'get',
    params: query
  })
}

// 查询医院信息详细
export function getYyYy(id) {
  return request({
    url: '/jkza/yyYy/' + id,
    method: 'get'
  })
}

// 新增医院信息
export function addYyYy(data) {
  return request({
    url: '/jkza/yyYy',
    method: 'post',
    data: data
  })
}

// 修改医院信息
export function updateYyYy(data) {
  return request({
    url: '/jkza/yyYy',
    method: 'put',
    data: data
  })
}

// 删除医院信息
export function delYyYy(id) {
  return request({
    url: '/jkza/yyYy/' + id,
    method: 'delete'
  })
}
