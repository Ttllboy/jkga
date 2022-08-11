import request from '@/utils/request'

// 查询视频设备列表
export function listYyVideo(query) {
  return request({
    url: '/jkza/yyVideo/list',
    method: 'get',
    params: query
  })
}

// 查询视频设备详细
export function getYyVideo(id) {
  return request({
    url: '/jkza/yyVideo/' + id,
    method: 'get'
  })
}

// 新增视频设备
export function addYyVideo(data) {
  return request({
    url: '/jkza/yyVideo',
    method: 'post',
    data: data
  })
}

// 修改视频设备
export function updateYyVideo(data) {
  return request({
    url: '/jkza/yyVideo',
    method: 'put',
    data: data
  })
}

// 删除视频设备
export function delYyVideo(id) {
  return request({
    url: '/jkza/yyVideo/' + id,
    method: 'delete'
  })
}
