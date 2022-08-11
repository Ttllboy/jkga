import request from '@/utils/request'

// 查询视频设备列表
export function listGdVideo(query) {
  return request({
    url: '/jkza/gdVideo/list',
    method: 'get',
    params: query
  })
}

// 查询视频设备详细
export function getGdVideo(id) {
  return request({
    url: '/jkza/gdVideo/' + id,
    method: 'get'
  })
}

// 新增视频设备
export function addGdVideo(data) {
  return request({
    url: '/jkza/gdVideo',
    method: 'post',
    data: data
  })
}

// 修改视频设备
export function updateGdVideo(data) {
  return request({
    url: '/jkza/gdVideo',
    method: 'put',
    data: data
  })
}

// 删除视频设备
export function delGdVideo(id) {
  return request({
    url: '/jkza/gdVideo/' + id,
    method: 'delete'
  })
}
