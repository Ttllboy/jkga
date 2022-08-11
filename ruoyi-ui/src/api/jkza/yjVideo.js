import request from '@/utils/request'

// 查询视频设备列表
export function listYjVideo(query) {
  return request({
    url: '/jkza/yjVideo/list',
    method: 'get',
    params: query
  })
}

// 查询视频设备详细
export function getYjVideo(id) {
  return request({
    url: '/jkza/yjVideo/' + id,
    method: 'get'
  })
}

// 新增视频设备
export function addYjVideo(data) {
  return request({
    url: '/jkza/yjVideo',
    method: 'post',
    data: data
  })
}

// 修改视频设备
export function updateYjVideo(data) {
  return request({
    url: '/jkza/yjVideo',
    method: 'put',
    data: data
  })
}

// 删除视频设备
export function delYjVideo(id) {
  return request({
    url: '/jkza/yjVideo/' + id,
    method: 'delete'
  })
}
