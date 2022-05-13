import request from '@/utils/request'

// 查询基础数据列表
export function listYyBase(query) {
  return request({
    url: '/jkza/yyBase/list',
    method: 'get',
    params: query
  })
}

// 查询基础数据详细
export function getYyBase(id) {
  return request({
    url: '/jkza/yyBase/' + id,
    method: 'get'
  })
}

// 新增基础数据
export function addYyBase(data) {
  return request({
    url: '/jkza/yyBase',
    method: 'post',
    data: data
  })
}

// 修改基础数据
export function updateYyBase(data) {
  return request({
    url: '/jkza/yyBase',
    method: 'put',
    data: data
  })
}

// 删除基础数据
export function delYyBase(id) {
  return request({
    url: '/jkza/yyBase/' + id,
    method: 'delete'
  })
}
