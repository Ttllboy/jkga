import request from '@/utils/request'

// 查询扬尘设备列表
export function listGdDustEquipment(query) {
  return request({
    url: '/jkza/gdDustEquipment/list',
    method: 'get',
    params: query
  })
}

// 查询扬尘设备详细
export function getGdDustEquipment(id) {
  return request({
    url: '/jkza/gdDustEquipment/' + id,
    method: 'get'
  })
}

// 新增扬尘设备
export function addGdDustEquipment(data) {
  return request({
    url: '/jkza/gdDustEquipment',
    method: 'post',
    data: data
  })
}

// 修改扬尘设备
export function updateGdDustEquipment(data) {
  return request({
    url: '/jkza/gdDustEquipment',
    method: 'put',
    data: data
  })
}

// 删除扬尘设备
export function delGdDustEquipment(id) {
  return request({
    url: '/jkza/gdDustEquipment/' + id,
    method: 'delete'
  })
}
