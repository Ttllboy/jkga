import request from '@/utils/request'

// 查询视频列表列表
export function listYyVideoBase(query) {
  return request({
    url: '/jkza/yyVideoBase/list',
    method: 'get',
    params: query
  })
}

// 查询视频列表详细
export function getYyVideoBase(id) {
  return request({
    url: '/jkza/yyVideoBase/' + id,
    method: 'get'
  })
}

// 新增视频列表
export function addYyVideoBase(data) {
  return request({
    url: '/jkza/yyVideoBase',
    method: 'post',
    data: data
  })
}

// 修改视频列表
export function updateYyVideoBase(data) {
  return request({
    url: '/jkza/yyVideoBase',
    method: 'put',
    data: data
  })
}

// 删除视频列表
export function delYyVideoBase(id) {
  return request({
    url: '/jkza/yyVideoBase/' + id,
    method: 'delete'
  })
}
