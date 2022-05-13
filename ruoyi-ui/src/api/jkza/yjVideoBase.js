import request from '@/utils/request'

// 查询视频列表列表
export function listYjVideoBase(query) {
  return request({
    url: '/jkza/yjVideoBase/list',
    method: 'get',
    params: query
  })
}

// 查询视频列表详细
export function getYjVideoBase(id) {
  return request({
    url: '/jkza/yjVideoBase/' + id,
    method: 'get'
  })
}

// 新增视频列表
export function addYjVideoBase(data) {
  return request({
    url: '/jkza/yjVideoBase',
    method: 'post',
    data: data
  })
}

// 修改视频列表
export function updateYjVideoBase(data) {
  return request({
    url: '/jkza/yjVideoBase',
    method: 'put',
    data: data
  })
}

// 删除视频列表
export function delYjVideoBase(id) {
  return request({
    url: '/jkza/yjVideoBase/' + id,
    method: 'delete'
  })
}
