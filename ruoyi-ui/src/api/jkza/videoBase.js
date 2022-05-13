import request from '@/utils/request'

// 查询视频列表列表
export function listVideoBase(query) {
  return request({
    url: '/jkza/videoBase/list',
    method: 'get',
    params: query
  })
}

// 查询视频列表详细
export function getVideoBase(id) {
  return request({
    url: '/jkza/videoBase/' + id,
    method: 'get'
  })
}

// 新增视频列表
export function addVideoBase(data) {
  return request({
    url: '/jkza/videoBase',
    method: 'post',
    data: data
  })
}

// 修改视频列表
export function updateVideoBase(data) {
  return request({
    url: '/jkza/videoBase',
    method: 'put',
    data: data
  })
}

// 删除视频列表
export function delVideoBase(id) {
  return request({
    url: '/jkza/videoBase/' + id,
    method: 'delete'
  })
}
