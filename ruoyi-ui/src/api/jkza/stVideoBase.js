import request from '@/utils/request'

// 查询视频列表列表
export function listStVideoBase(query) {
  return request({
    url: '/jkza/stVideoBase/list',
    method: 'get',
    params: query
  })
}

// 查询视频列表详细
export function getStVideoBase(id) {
  return request({
    url: '/jkza/stVideoBase/' + id,
    method: 'get'
  })
}

// 新增视频列表
export function addStVideoBase(data) {
  return request({
    url: '/jkza/stVideoBase',
    method: 'post',
    data: data
  })
}

// 修改视频列表
export function updateStVideoBase(data) {
  return request({
    url: '/jkza/stVideoBase',
    method: 'put',
    data: data
  })
}

// 删除视频列表
export function delStVideoBase(id) {
  return request({
    url: '/jkza/stVideoBase/' + id,
    method: 'delete'
  })
}
