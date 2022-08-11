import request from '@/utils/request'

// 查询视频设备列表
export function listStVideo(query) {
  return request({
    url: '/jkza/stVideo/list',
    method: 'get',
    params: query
  })
}

// 查询视频设备详细
export function getStVideo(id) {
  return request({
    url: '/jkza/stVideo/' + id,
    method: 'get'
  })
}

// 新增视频设备
export function addStVideo(data) {
  return request({
    url: '/jkza/stVideo',
    method: 'post',
    data: data
  })
}

// 修改视频设备
export function updateStVideo(data) {
  return request({
    url: '/jkza/stVideo',
    method: 'put',
    data: data
  })
}

// 删除视频设备
export function delStVideo(id) {
  return request({
    url: '/jkza/stVideo/' + id,
    method: 'delete'
  })
}
