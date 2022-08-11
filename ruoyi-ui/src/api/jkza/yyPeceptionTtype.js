import request from '@/utils/request'

// 查询感知筛选列表
export function listYyPeceptionTtype(query) {
  return request({
    url: '/jkza/yyPeceptionTtype/list',
    method: 'get',
    params: query
  })
}

// 查询感知筛选详细
export function getYyPeceptionTtype(id) {
  return request({
    url: '/jkza/yyPeceptionTtype/' + id,
    method: 'get'
  })
}

// 新增感知筛选
export function addYyPeceptionTtype(data) {
  return request({
    url: '/jkza/yyPeceptionTtype',
    method: 'post',
    data: data
  })
}

// 修改感知筛选
export function updateYyPeceptionTtype(data) {
  return request({
    url: '/jkza/yyPeceptionTtype',
    method: 'put',
    data: data
  })
}

// 删除感知筛选
export function delYyPeceptionTtype(id) {
  return request({
    url: '/jkza/yyPeceptionTtype/' + id,
    method: 'delete'
  })
}
