import request from '@/utils/request'

// 查询感知信息列表
export function listYyPerception(query) {
  return request({
    url: '/jkza/yyPerception/list',
    method: 'get',
    params: query
  })
}

// 查询感知信息详细
export function getYyPerception(id) {
  return request({
    url: '/jkza/yyPerception/' + id,
    method: 'get'
  })
}

// 新增感知信息
export function addYyPerception(data) {
  return request({
    url: '/jkza/yyPerception',
    method: 'post',
    data: data
  })
}

// 修改感知信息
export function updateYyPerception(data) {
  return request({
    url: '/jkza/yyPerception',
    method: 'put',
    data: data
  })
}

// 删除感知信息
export function delYyPerception(id) {
  return request({
    url: '/jkza/yyPerception/' + id,
    method: 'delete'
  })
}
