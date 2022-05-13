import request from '@/utils/request'

// 查询精神病人列表
export function listYyMmental(query) {
  return request({
    url: '/jkza/yyMmental/list',
    method: 'get',
    params: query
  })
}

// 查询精神病人详细
export function getYyMmental(id) {
  return request({
    url: '/jkza/yyMmental/' + id,
    method: 'get'
  })
}

// 新增精神病人
export function addYyMmental(data) {
  return request({
    url: '/jkza/yyMmental',
    method: 'post',
    data: data
  })
}

// 修改精神病人
export function updateYyMmental(data) {
  return request({
    url: '/jkza/yyMmental',
    method: 'put',
    data: data
  })
}

// 删除精神病人
export function delYyMmental(id) {
  return request({
    url: '/jkza/yyMmental/' + id,
    method: 'delete'
  })
}
