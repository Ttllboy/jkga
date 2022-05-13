import request from '@/utils/request'

// 查询视频设备列表
export function listVideoEquipment(query) {
  return request({
    url: '/jkza/videoEquipment/list',
    method: 'get',
    params: query
  })
}

// 查询视频设备详细
export function getVideoEquipment(id) {
  return request({
    url: '/jkza/videoEquipment/' + id,
    method: 'get'
  })
}

// 新增视频设备
export function addVideoEquipment(data) {
  return request({
    url: '/jkza/videoEquipment',
    method: 'post',
    data: data
  })
}

// 修改视频设备
export function updateVideoEquipment(data) {
  return request({
    url: '/jkza/videoEquipment',
    method: 'put',
    data: data
  })
}

// 删除视频设备
export function delVideoEquipment(id) {
  return request({
    url: '/jkza/videoEquipment/' + id,
    method: 'delete'
  })
}
