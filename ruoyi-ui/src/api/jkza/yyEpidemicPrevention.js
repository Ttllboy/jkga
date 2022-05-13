import request from '@/utils/request'

// 查询防疫详情列表
export function listYyEpidemicPrevention(query) {
  return request({
    url: '/jkza/yyEpidemicPrevention/list',
    method: 'get',
    params: query
  })
}

// 查询防疫详情详细
export function getYyEpidemicPrevention(id) {
  return request({
    url: '/jkza/yyEpidemicPrevention/' + id,
    method: 'get'
  })
}

// 新增防疫详情
export function addYyEpidemicPrevention(data) {
  return request({
    url: '/jkza/yyEpidemicPrevention',
    method: 'post',
    data: data
  })
}

// 修改防疫详情
export function updateYyEpidemicPrevention(data) {
  return request({
    url: '/jkza/yyEpidemicPrevention',
    method: 'put',
    data: data
  })
}

// 删除防疫详情
export function delYyEpidemicPrevention(id) {
  return request({
    url: '/jkza/yyEpidemicPrevention/' + id,
    method: 'delete'
  })
}
